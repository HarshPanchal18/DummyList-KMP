package data

val regex = "\\{([^{}]*)}".toRegex()

inline fun <reified T : Any> String.getUrl(vararg params: T): String {
    val paramMap = mutableMapOf<String, Any>()

    for ((index, param) in params.withIndex()) // .withIndex() wraps each element of the params into an IndexedValue containing the index of that element and the element itself.
        paramMap["param$index"] = param // Storing values to the paramMap on param`I`th index

    val paramNames = regex.findAll(this).map { it.groupValues[1] }.toList()
    val paramMapWithName = mutableMapOf<String, Any>()

    for (name in paramNames) {
        if (paramMap.containsKey("param${paramNames.indexOf(name)}")) // If there's already a key with `name` in paramMap, then it will use that value
            paramMapWithName[name] = paramMap["param${paramNames.indexOf(name)}"] ?: ""
        else if (!name.contains("{") && !name.contains("}")) // Otherwise it will create its own key with `name` and store value with the key.
            paramMapWithName[name] = name
    }

    return this.replaceParams(paramMapWithName.toParamMap<T>())
}

// reified - access the information related to a class at runtime. It can only be used within inline functions
// When "reified" keyword is used, the compiler copies the function's bytecode to every section of the code where the function has been called.
inline fun <reified T : Any> Map<String, Any>.toParamMap(): Map<String, String> {
    val paramMap = mutableMapOf<String, String>()

    // Store value on key if the value match with the type of T
    for ((key, value) in this)
        if (value is T)
            paramMap[key] = value.toString()

    return paramMap
}

fun String.replaceParams(paramMap: Map<String, String>): String {
    val result = StringBuilder()
    var index = 0

    while (index < this.length) {

        val startIndex = this.indexOf('{', index) // First occurrence of '{'
        if (startIndex == -1) { // If '{' is not found into String
            // Append substring started from index to the end of String and break out the loop
            result.append(this.substring(index))
            break
        }

        val endIndex = this.indexOf('}', startIndex)
        if (endIndex == -1) {
            result.append(this.substring(index))
            break
        }

        result.append(this.substring(index, startIndex)) // Append whatever text was found before '{'

        val key = this.substring(startIndex + 1, endIndex) // Set `key` name
        val value = paramMap[key] ?: key // If paramMap doesn't exist for key, then its value will default to being equal to itself.
        result.append(value)
        index = endIndex + 1 // Move index one step forward

    }

    return result.toString()
}
