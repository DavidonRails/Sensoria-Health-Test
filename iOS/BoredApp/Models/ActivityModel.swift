//
//  ActivityModel.swift
//  BoredApp
//
//  Created by Admin on 2022/5/31.
//

import Foundation

class ActivityModel: NSObject {
    var activity = ""
    var type = ""
    var participants = 0
    var price = 0.0
    var link = ""
    var key = ""
    var accessibility = 0
    
    override init() {
        super.init()
    }
    
    init(dict: [String: Any]) {
        if let val = dict["activity"] as? String     { activity = val }
        if let val = dict["type"] as? String         { type = val }
        if let val = dict["participants"] as? Int    { participants = val }
        if let val = dict["price"] as? Double        { price = val }
        if let val = dict["link"] as? String         { link = val }
        if let val = dict["key"] as? String          { key = val }
        if let val = dict["accessibility"] as? Int   { accessibility = val }
    }
    
    func getDictionary() -> [String: Any] {
        let body: [String: Any] = [
            "activity": activity,
            "type": type,
            "participants": participants,
            "price": price,
            "link": link,
            "key": key,
            "accessibility": accessibility
        ]
        return body
    }
}
