//
//  SearchView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/01.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SearchView: View {
    
    @EnvironmentObject var rootResourcesState: RootResourcesState
    @EnvironmentObject var searchState: SearchState
    
    @State private var query = ""
    
    @State private var currentTokens = [ResourceToken]([])
    
    var body: some View {
        NavigationView {
            
            ZStack {
                
                if let items = searchState.items {
                    List(items) { item in
                        ItemContent(name: item.name, date: item.date)
                            .listRowSeparator(.hidden)
                            .onAppear {
                                if (items.last == item) {
                                    searchState.searchMoreResources(resourceType: currentTokens[0].name.lowercased())
                                }
                            }
                    }
                }
                
                if searchState.loading {
                    if (searchState.items?.count == 0) {
                        LoadingContent()
                    }
                }
                
                if let errorMessage = searchState.error {
                    RetryContent(
                        error: errorMessage,
                        retry: {
                            
                        }
                    )
                }
                
            }
            .navigationTitle("Search")
            .searchable(
                text: $query,
                tokens: $currentTokens
            ) { token in
                Label(token.name, systemImage: "line.3.horizontal.decrease.circle")
            }
            .searchSuggestions({
                if (currentTokens.isEmpty) {
                    ScrollView(.vertical, showsIndicators: false) {
                        LazyVStack {
                            if let resourcesDictionary = rootResourcesState.resources {
                                ForEach(Array(resourcesDictionary), id:\.key) { key, value in
                                    HStack {
                                        Text(key.capitalized)
                                        Spacer()
                                        Image(systemName: "arrow.up.left.circle")
                                    }
                                    .padding(12)
                                    .background(.gray.opacity(0.1))
                                    .cornerRadius(5)
                                    .contentShape(Rectangle())
                                    .onTapGesture {
                                        if (!currentTokens.isEmpty) {
                                            currentTokens.removeAll()
                                        }
                                        currentTokens.append(ResourceToken(name: key.capitalized))
                                    }
                                }
                            }
                        }
                    }
                    .background(.white)
                }
            })
            .onSubmit(of: .search) {
                print(query)
                print(currentTokens)
                searchState.searchResources(resourceType: currentTokens[0].name.lowercased(), search: query, page: 1)
            }
        }
    }
    
}

struct Token: Identifiable {
    let id = UUID()
    let value: String
}
    
struct SearchingView: View {
    @State private var query = ""
    @State private var tokens: [Token] = []
    @State private var suggestedTokens: [Token] = [
        .init(value: "Token1"),
        .init(value: "Token2"),
        .init(value: "Token3"),
        .init(value: "Token4")
    ]
    
    var body: some View {
        NavigationStack {
            List {
                // ...
            }
            .searchable(
                text: $query,
                tokens: $tokens,
                suggestedTokens: $suggestedTokens
            ) { token in
                Text(verbatim: token.value)
            }
            .navigationTitle("Search")
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
            .environmentObject(SearchState())
    }
}
