//
//  CellContent.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/25.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct CellContent: View {
    var title: String
    var url: String
    var body: some View {
        VStack {
            Text(title.capitalized)
                .font(.system(size: 20))
                .foregroundColor(.white)
                .frame(maxWidth: .infinity, alignment: .topLeading)
                .padding(10)
            Spacer()
            Image(systemName: "arrow.right")
                .resizable()
                .scaledToFit()
                .foregroundColor(.white)
                .frame(width: 30, height: 30)
                .padding(10)
                .offset(x: 40)
        }
        .frame(minWidth: 40, maxWidth: .infinity, minHeight: 150)
        .background(Color.black)
        .cornerRadius(8)
    }
}

struct CellContent_Previews: PreviewProvider {
    static var previews: some View {
        CellContent(title: "Title", url: "url")
    }
}
