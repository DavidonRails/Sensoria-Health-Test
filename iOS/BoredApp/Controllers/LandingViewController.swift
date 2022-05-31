//
//  LandingViewController.swift
//  BoredApp
//
//  Created by Admin on 2022/5/31.
//

import UIKit

class LandingViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    @IBAction func onNext(_ sender: Any) {
        let vc = storyboard?.instantiateViewController(withIdentifier: "CardViewController") as! CardViewController
        navigationController?.pushViewController(vc, animated: true)
    }
}
