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
    
    var resources: [String:String]?
    
    @State private var query = ""
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading) {
                List {
                    Section {
                        if let resourcesDictionary = resources {
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
            .searchable(text: $query, placement: .navigationBarDrawer(displayMode: .always))
            .navigationBarTitleDisplayMode(.automatic)
        }
    }
}

/*struct SearchView_Previews: PreviewProvider {
    static var previews: some View {
        SearchView()
    }
}*/
