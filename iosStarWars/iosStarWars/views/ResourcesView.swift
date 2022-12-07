//
//  ResourcesView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/07.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ResourcesView: View {
    
    var body: some View {
        NavigationLink(destination: ResourceView()){
            Text("Resources list with pagging")
        }
        .navigationBarTitle("Resources")
    }
}

struct ResourcesView_Previews: PreviewProvider {
    static var previews: some View {
        ResourcesView()
    }
}
