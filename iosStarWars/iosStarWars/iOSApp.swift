import SwiftUI
import data

@main
struct iOSApp: App {
    
    @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate
    
    @StateObject var rootResourceState = RootResourcesState()
    @StateObject var resourcesState = ResourcesState()
    @StateObject var resourceState = ResourceState()
    @StateObject var searchState = SearchState()
    
	var body: some Scene {
		WindowGroup {
			ContentView()
                .environmentObject(rootResourceState)
                .environmentObject(resourcesState)
                .environmentObject(resourceState)
                .environmentObject(searchState)
		}
	}
}

class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        CommonModuleKt.doInitKoin()
        return true
    }
}
