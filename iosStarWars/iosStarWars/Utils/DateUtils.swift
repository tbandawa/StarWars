//
//  DateUtils.swift
//  StarWars
//
//  Created by Tendai Bandawa on 2023/07/03.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct DateUtils {
    
    private static let resourceDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
    private static let viewDateFormat = "MMMM d, yyyy"
    private static let dateFormatter = DateFormatter()
    
    static func formatDate(resourceDate: String) -> String {
        dateFormatter.dateFormat = resourceDateFormat
        let date = dateFormatter.date(from: resourceDate)
        dateFormatter.dateFormat = viewDateFormat
        let stringDate = dateFormatter.string(from: date!)
        return stringDate
    }
}
