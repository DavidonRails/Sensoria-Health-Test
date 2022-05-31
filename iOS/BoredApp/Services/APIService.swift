//
//  APIService.swift
//  BoredApp
//
//  Created by Admin on 2022/5/31.
//

import Foundation
import Alamofire

class APIService {
    
    static let shared = APIService()
    private init() {
        
    }
    
    func getActivity(completion: @escaping (Error?, Any?, Int?)->()) {

        let url = Constants.GET_ACTIVITY

        AF.request(URL(string: url)!, method: .get, encoding: JSONEncoding.default).responseJSON {
            response in

            switch response.result {
            case .success(let data):
                let status = response.response?.statusCode ?? 0
                completion(nil, data, status)
            case .failure(let error):
                completion(error, nil, 0)
            }
        }
    }
}
