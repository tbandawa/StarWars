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
    
    @State private var query = ""
    
    @State private var currentTokens = [ResourceToken]()
    
    var suggestedTokens: [ResourceToken] {
        var tokens = [ResourceToken]()
        if let resourcesDictionary = rootResourcesState.resources {
            resourcesDictionary.forEach { value in
                tokens.append(ResourceToken(name: value.key.capitalized))
            }
            return tokens
        }
        return []
    }
    
    var body: some View {
        NavigationView {
            ZStack(alignment: .leading) {
                List {
                    Section {
                        if let resourcesDictionary = rootResourcesState.resources {
                            ForEach(Array(resourcesDictionary), id:\.key) { key, value in
                                Text(key.capitalized)
                            }
                        }
                    } header: {
                        Text("Search Filter")
                    }
                }
                .listStyle(.plain)
            }
            .navigationTitle("Search")
            .searchable(
                text: $query,
                tokens: $currentTokens,
                suggestedTokens: .constant(suggestedTokens)
            ) { token in
                Text(token.name)
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
