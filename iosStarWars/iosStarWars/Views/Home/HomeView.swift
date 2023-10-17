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
                
                if let resourcesDictionary = rootResourcesState.resources {
                    LazyVGrid(columns: gridItems, spacing: 10) {
                        ForEach(Array(resourcesDictionary), id:\.key) { key, value in
                            NavigationLink(destination: ResourcesView(title: key.capitalized)){
                                CellContent(title: key, url: value)
                            }
                        }
                    }
                    .padding(.leading, 15)
                    .padding(.trailing, 15)
                    Spacer()
                }
                
                if let errorMessage = rootResourcesState.error {
                    RetryContent(
                        error: errorMessage,
                        retry: { rootResourcesState.getRootResources() }
                    )
                }
                
                if rootResourcesState.loading {
                    LoadingContent()
                }
                
            }
            .navigationTitle("StarWars")
            .frame(maxWidth: .infinity, maxHeight: .infinity)
            
        }.onAppear {
            rootResourcesState.getRootResources()
        }
        .navigationViewStyle(.stack)
	}
    
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
            .environmentObject(RootResourcesState())
    }
}
