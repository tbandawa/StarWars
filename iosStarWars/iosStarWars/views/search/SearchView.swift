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
    
    @State private var currentTokens = [ResourceToken]([])
    @State private var showingSearchErrorAlert = false
    @State private var query = ""
    
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
                        error: "errorMessage",
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
                        VStack {
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
                }
            })
            .onSubmit(of: .search) {
                if (currentTokens.count == 1) {
                    searchState.searchResources(resourceType: currentTokens[0].name.lowercased(), search: query, page: 1)
                } else {
                    showingSearchErrorAlert.toggle()
                }
            }
        }
        .alert("Select A Type Of Resource You Would Like To Search", isPresented: $showingSearchErrorAlert) {
            Button("OK", role: .cancel) { }
        }
        
    }
    
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
            .environmentObject(SearchState())
    }
}
