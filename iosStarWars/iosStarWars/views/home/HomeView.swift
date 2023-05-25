import SwiftUI
import Foundation
import data

struct HomeView: View {
    
    @EnvironmentObject var rootResourcesState: RootResourcesState
    
    var colors: [Color] = [.black, .black, .black]
    
    var gridItems = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    var body: some View {
        NavigationView {
            VStack {
                if rootResourcesState.loading {
                    LoadingContent()
                }
                if let resourcesDictionary = rootResourcesState.resources {
                    LazyVGrid(columns: gridItems, spacing: 10) {
                        ForEach(Array(resourcesDictionary), id:\.key) { key, value in
                            NavigationLink(destination: ResourcesView(title: key.capitalized, resourceUrl: value)){
                                CellContent(title: key, url: value)
                            }
                        }
                    }
                    Spacer()
                }
                if let errorMessage = rootResourcesState.error {
                    RetryContent(
                        error: errorMessage,
                        retry: { rootResourcesState.getRootResources() }
                    )
                }
            }
            .navigationTitle("StarWars")
            .padding(.leading, 15)
            .padding(.trailing, 15)
        }.onAppear {
            rootResourcesState.getRootResources()
        }
	}
}


