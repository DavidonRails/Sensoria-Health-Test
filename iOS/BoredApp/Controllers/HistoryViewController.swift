//
//  HistoryViewController.swift
//  BoredApp
//
//  Created by Admin on 2022/5/31.
//

import UIKit

class HistoryViewController: UIViewController {
    
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
    
    @IBAction func onBack(_ sender: Any) {
        navigationController?.popViewController(animated: true)
    }
    
    @IBAction func onCloseCard(_ sender: Any) {
        cardConView.isHidden = true
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
    }
}

extension HistoryViewController: UICollectionViewDelegate {
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let activity = activities[indexPath.item]
        showActivity(activity: activity)
    }
}

extension HistoryViewController: UICollectionViewDataSource {
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return activities.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "ActivityCollectionViewCell", for: indexPath) as! ActivityCollectionViewCell
        let activity = activities[indexPath.item]
        cell.lblActivity.text = activity.activity
        return cell
    }
}

extension HistoryViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        let height = 60.0
        let width = collectionView.frame.width
        return CGSize(width: width, height: height)
    }
}

