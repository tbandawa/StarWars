//
//  SearchView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/01.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct Friend: Identifiable, Hashable { // Example model
    let id: UUID = .init()
    let name: String
}

struct SearchView: View {
    
    @State private var query = ""
    
    @State var isSearching = false
    
    @State var resources: [Friend] = [
        .init(name: "People"),
        .init(name: "Vehicles"),
        .init(name: "Planets"),
        .init(name: "Films"),
        .init(name: "Species"),
        .init(name: "Starships"),
    ]
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading) {
                List {
                    Section {
                        ForEach(resources, id: \.self) { resource in
                            Text(resource.name)
                        }
                    } header: {
                        Text("Recent Searches")
                    }
                }
                .listStyle(.plain)
            }
            .navigationTitle("Search")
            .searchable(text: $query){
                
            }
            .onChange(of: query) { newQuery in
                print(query)
            }
        }
    }
}

struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}
