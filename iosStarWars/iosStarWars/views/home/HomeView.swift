import SwiftUI
import Foundation
import data

struct HomeView: View {
    
    var loading: Bool
    var resources: [String:String]?
    var error: String?
    var loadResources: () -> Void
    var retry: () -> Void
    
    var colors: [Color] = [.black, .black, .black]
    
    var gridItems = [
        GridItem(.flexible()),
        GridItem(.flexible()),
        GridItem(.flexible())
    ]

    var body: some View {
        NavigationView {
            VStack {
                if loading {
                    LoadingContent()
                }
                if let resourcesDictionary = resources {
                    LazyVGrid(columns: gridItems, spacing: 10) {
                        ForEach(Array(resourcesDictionary), id:\.key) { key, value in
                            NavigationLink(destination: ResourcesView(title: key.capitalized, resourceUrl: value)){
                                CellContent(title: key, url: value)
                            }
                        }
                    }
                    Spacer()
                }
                if let errorMessage = error {
                    RetryContent(
                        error: errorMessage,
                        retry: retry
                    )
                }
            }
            .navigationTitle("StarWars")
            .padding(.leading, 15)
            .padding(.trailing, 15)
        }.onAppear {
            loadResources()
        }
	}
}


