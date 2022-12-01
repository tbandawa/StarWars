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
            VStack{
                Spacer()
            }
            .navigationTitle("Search")
            .searchable(text: $query, placement: .navigationBarDrawer(displayMode: .automatic)){
                
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
