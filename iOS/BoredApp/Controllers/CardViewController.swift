//
//  CardViewController.swift
//  BoredApp
//
//  Created by Admin on 2022/5/31.
//

import UIKit

class CardViewController: UIViewController {

    @IBOutlet weak var cardConView: UIView!
    @IBOutlet weak var cardView: UIView!
    @IBOutlet weak var lblActivity: UILabel!
    @IBOutlet weak var lblType: UILabel!
    @IBOutlet weak var lblParticipants: UILabel!
    @IBOutlet weak var lblPrice: UILabel!
    @IBOutlet weak var lblLink: UILabel!
    @IBOutlet weak var lblKey: UILabel!
    @IBOutlet weak var lblAccessibility: UILabel!
    
    @IBOutlet weak var btnClose: UIButton!
    
    
    var activities: [ActivityModel] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()

        configureView()
        getSavedActivities()
    }
    
    func configureView() {
        cardView.layer.cornerRadius = 10
        cardView.layer.borderWidth = 0.5
        cardView.layer.borderColor = UIColor(named: "colorBorderGray")!.cgColor
        cardView.layer.masksToBounds = false
        cardView.layer.shadowColor = UIColor(named: "colorBorderGray")!.cgColor
        cardView.shadowRadius = 8
        cardView.layer.shadowOffset = CGSize(width: 0, height: 3)
        cardView.layer.shadowOpacity = 0.5
        cardConView.isHidden = true
        
        btnClose.setImage(UIImage(named: "ic_close")!.withRenderingMode(.alwaysTemplate), for: .normal)
        btnClose.tintColor = UIColor(named: "colorText")
    }
    
    func getSavedActivities() {
        let savedArray = UserDefaults.standard.array(forKey: "ACTIVITIES")
        if savedArray != nil {
            for item in savedArray! {
                let activity = ActivityModel(dict: item as! [String: Any])
                activities.append(activity)
            }
        }
    }

    @IBAction func onCloseCard(_ sender: Any) {
        cardConView.isHidden = true
    }
    
    @IBAction func onHistory(_ sender: Any) {
        let vc = storyboard?.instantiateViewController(withIdentifier: "HistoryViewController") as! HistoryViewController
        vc.activities = activities.reversed()
        navigationController?.pushViewController(vc, animated: true)
    }
    
    @IBAction func onGetActivity(_ sender: Any) {
        ProgressHUD.shared.show(view: view, msg: "")
        APIService.shared.getActivity(completion: {
            error, res, status in
            ProgressHUD.shared.dismiss()
            if error != nil {
                self.showAlert(title: "Error", msg: error!.localizedDescription)
            } else {
                if status == 200 {
                    let activity = ActivityModel(dict: res as! [String: Any])
                    self.showActivity(activity: activity)
                }
            }
        })
    }
    
    func showActivity(activity: ActivityModel) {
        lblActivity.text = activity.activity
        lblType.text = activity.type
        lblParticipants.text = "\(activity.participants)"
        lblPrice.text = "\(activity.price)"
        lblLink.text = activity.link
        lblKey.text = activity.key
        lblAccessibility.text = "\(activity.accessibility)"
        cardConView.isHidden = false
        
        var isExist = false
        for item in activities {
            if item.key == activity.key {
                isExist = true
                break
            }
        }
        
        if !isExist {
            activities.append(activity)
        }
        
        var saveArray: [[String: Any]] = []
        for item in activities {
            saveArray.append(item.getDictionary())
        }
        
        UserDefaults.standard.set(saveArray, forKey: "ACTIVITIES")
    }
}
