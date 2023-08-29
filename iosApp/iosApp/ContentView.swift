import UIKit
import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()
    }
}

struct ContentView: View {
    var body: some View {
        ComposeView().ignoreSafeArea(.all, edges: .bottom)
    }
}
