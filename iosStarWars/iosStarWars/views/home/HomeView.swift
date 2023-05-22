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

struct CellContent: View {
    var title: String
    var url: String
    var body: some View {
        VStack {
            Text(title.capitalized)
                .font(.system(size: 20))
                .foregroundColor(.white)
                .frame(maxWidth: .infinity, alignment: .topLeading)
                .padding(10)
            Spacer()
            Image(systemName: "arrow.right")
                .resizable()
                .scaledToFit()
                .foregroundColor(.white)
                .frame(width: 30, height: 30)
                .padding(10)
                .offset(x: 40)
        }
        .frame(minWidth: 40, maxWidth: .infinity, minHeight: 150)
        .background(Color.black)
        .cornerRadius(8)
    }
}
