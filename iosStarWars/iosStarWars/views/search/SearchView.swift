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
    
    var body: some View {
        NavigationView {
            
            ScrollView {
                
                
                
                
                
                
            
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
        }
    }
    
}



struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
            .environmentObject(RootResourcesState())
    }
}
