//
//  RetryContent.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/05/20.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct RetryContent: View {
    
    var error: String
    var retry: () -> Void
    
    var body: some View {
        VStack {
            Text(error)
            Button("Retry") {
                retry()
            }
            .frame(width: 80, height: 35)
            .foregroundColor(Color.white)
            .background(Color.black)
            .cornerRadius(15)
        }
    }
}

struct RetryContent_Previews: PreviewProvider {
    static var previews: some View {
        RetryContent(error: "Error message") { }
    }
}
