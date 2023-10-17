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
                                    if currentTokens.count > 0 {
                                        searchState.searchMoreResources(resourceType: currentTokens[0].name.lowercased())
                                    }
                                }
                            }
                            .background( NavigationLink("", destination: ResourceView(item: item)).opacity(0) )
                    }
                }
                
                if let result = searchState.resources {
                    if result.count == 0 {
                        EmptyResultsView()
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
                            if (currentTokens.count == 1) {
                                searchState.searchResources(resourceType: currentTokens[0].name.lowercased(), search: query, page: 1)
                            } else {
                                showingSearchErrorAlert.toggle()
                            }
                        }
                    )
                }
                
            }
            .navigationTitle("Search")
            .searchable(
                text: $query,
                tokens: $currentTokens
            ) { token in
                Label(token.name, systemImage: "line.3.horizontal.decrease")
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
            .onAppear{
                searchState.loading = false
            }
        }
        .onDisappear {
            searchState.cancelJob()
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
