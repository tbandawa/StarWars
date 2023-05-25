//
//  ResourceItem.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/25.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct ResourceItem: View {
    var name: String
    var date: String
    var body: some View {
        HStack {
            
            VStack(alignment: .leading) {
                
                HStack {
                    Text(name)
                        .font(.system(size: 14, design: .rounded))
                        .foregroundColor(Color.white)
                        .fontWeight(.bold)
                    Spacer()
                }
                .padding(.bottom, 1)
                
                Text(date)
                    .font(.system(size: 12, design: .rounded))
                    .foregroundColor(Color.white)
                
            }
            .padding(10)
            .background(Color.black)
            .cornerRadius(8)
        }
        .padding()
        .frame(maxWidth: .infinity)
    }
}

struct ResourceItem_Previews: PreviewProvider {
    static var previews: some View {
        ResourceItem(name: "Luke Skywalker", date: "02-11-1991")
    }
}
