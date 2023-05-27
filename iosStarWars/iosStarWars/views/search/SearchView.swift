//
//  SearchView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/01.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct Friend: Identifiable, Hashable {
    let id: UUID = .init()
    let name: String
}

struct SearchView: View {
    
    @EnvironmentObject var rootResourcesState: RootResourcesState
    @EnvironmentObject var resourcesState: ResourcesState
    
    @State private var query = ""
    
    @State private var currentTokens = [ResourceToken]([])
    
    var suggestedTokens: [ResourceToken] {
        var tokens = [ResourceToken]()
        if let resourcesDictionary = rootResourcesState.resources {
            resourcesDictionary.forEach { value in
                tokens.append(ResourceToken(name: value.key.capitalized))
            }
        }
        return tokens
    }
    
    var body: some View {
        NavigationView {
            ZStack {
                
                List {
                    Section {
                        if let resourcesDictionary = rootResourcesState.resources {
                            ForEach(Array(resourcesDictionary), id:\.key) { key, value in
                                HStack {
                                    Text(key.capitalized)
                                    Spacer()
                                    Image(systemName: "arrow.up.left.circle")
                                }
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
                    header: {
                         Text("Search Filter")
                    }
                }
                .listStyle(.plain)
                
            }
            .navigationTitle("Search")
            .searchable(
                text: $query,
                tokens: $currentTokens
            ) { token in
                Text(token.name)
            }
            .onSubmit(of: .search) {
                print(query)
                print(currentTokens)
                resourcesState.searchResources(resourceType: currentTokens[0].name.lowercased(), search: query, page: 1)
            }
            .navigationBarTitleDisplayMode(.automatic)
        }
    }
    
}

struct ResourceToken: Identifiable {
    var id: String { name }
    var name: String
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
            .environmentObject(RootResourcesState())
    }
}
