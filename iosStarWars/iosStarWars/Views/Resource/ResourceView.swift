//
//  ResourceView.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2022/12/03.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ResourceView: View {
    
    var item: Item
    
    @EnvironmentObject var resourceState: ResourceState
    
    var body: some View {
        Text("Resource infomation => name: \(item.name), date: \(item.date), url: \(item.url)")
            .onAppear {
                resourceState.getResource(resourceUrl: item.url)
            }
            .navigationBarTitle(item.name)
    }
}

struct ResourceView_Previews: PreviewProvider {
    static var previews: some View {
        ResourceView(item: Item(name: "name", date: "date", url: "url"))
    }
}
