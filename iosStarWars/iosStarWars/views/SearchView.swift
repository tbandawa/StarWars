//
//  SearchView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/01.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct SearchView: View {
    
    @State private var query = ""

    let suggestions: [String] = [
        "People", "Vehicles", "Planets", "Films", "Species", "Starships"
    ]
    
    var body: some View {
        NavigationView {
            VStack(alignment: .leading){
                Text("Recent Searches")
                    .padding(.leading, 20)
                List{
                    VStack(alignment: .leading) {
                        Text("Planets")
                            .font(.headline)
                    }
                    VStack(alignment: .leading) {
                        Text("Species")
                            .font(.headline)
                    }
                    VStack(alignment: .leading) {
                        Text("People")
                            .font(.headline)
                    }
                    VStack(alignment: .leading) {
                        Text("Films")
                            .font(.headline)
                    }
                    VStack(alignment: .leading) {
                        Text("Vehicles")
                            .font(.headline)
                    }
                    VStack(alignment: .leading) {
                        Text("Starships")
                            .font(.headline)
                    }
                }
            }
            .navigationTitle("Search")
            .searchable(text: $query){
                VStack {
                    List {
                        Section {
                            Text("Item 1")
                            Text("Item 2")
                            Text("Item 3")
                        }
                        
                        Section {
                            Text("Item 4")
                            Text("Item 5")
                            Text("Item 6")
                        }
                    }.listStyle(InsetGroupedListStyle())
                    .environment(\.horizontalSizeClass, .regular)
                    ForEach(suggestions, id: \.self) { suggestion in
                        VStack(alignment: .leading) {
                            Text(suggestion)
                                .font(.headline)
                        }
                    }
                }
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
