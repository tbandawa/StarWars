//
//  ResourceItem.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/25.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ItemContent: View {
    
    var name: String
    var date: String
    
    @Environment(\.colorScheme) var colorScheme
    
    var body: some View {
        HStack {
            
            VStack(alignment: .leading) {
                
                HStack {
                    Text(name)
                        .font(.system(size: 14, design: .rounded))
                        .foregroundColor(colorScheme == .dark ? Color.black : Color.white)
                        .fontWeight(.bold)
                    Spacer()
                }
                .padding(.bottom, 1)
                
                Text(date)
                    .font(.system(size: 12, design: .rounded))
                    .foregroundColor(colorScheme == .dark ? Color.black : Color.white)
                    .foregroundColor(Color.white)
                
            }
            .padding(10)
            .background(colorScheme == .dark ? Color.white : Color.black)
            .cornerRadius(8)
        }
        .frame(maxWidth: .infinity)
    }
}

struct ItemContent_Previews: PreviewProvider {
    static var previews: some View {
        ItemContent(name: "Luke Skywalker", date: "02-11-1991")
    }
}
