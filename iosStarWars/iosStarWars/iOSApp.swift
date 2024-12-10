import SwiftUI
import data

@main
struct iOSApp: App {
    
    @StateObject var rootResourceState = RootResourcesState()
    @StateObject var resourcesState = ResourcesState()
    @StateObject var resourceState = ResourceState()
    @StateObject var searchState = SearchState()
    
    init() {
        CommonModuleKt.doInitKoin()
    }
    
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
