//
//  ProgressHUD.swift
//  BoredApp
//
//  Created by Admin on 2022/5/31.
//

import Foundation
import JGProgressHUD
import UIKit

class ProgressHUD {
    static let shared = ProgressHUD()
    let hud = JGProgressHUD(style: .dark)
    
    private init() {}
    
    func show(view: UIView, msg: String) {
        hud.textLabel.text = msg
        hud.show(in: view)
    }
    
    func setHudText(_ text: String) {
        hud.textLabel.text = text
    }
    
    func dismiss() {
        //hud.dismiss(afterDelay: 3.0)
        hud.dismiss()
    }
}
