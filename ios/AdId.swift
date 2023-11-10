//
//  RNAdvertisingId.swift
//
//  Created by Damilare Adegunloye on 10/11/2023.
//

import AdSupport
#if canImport(AppTrackingTransparency)
import AppTrackingTransparency
#endif

@objc(RNAdvertisingId)
class RNAdvertisingId: NSObject {
    
    @objc
    func getAdvertisingId(_ resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock) {
        #if os(iOS) && canImport(AppTrackingTransparency)
        if #available(iOS 14, *) {
            ATTrackingManager.requestTrackingAuthorization { status in
                self.proceedWithIdfaCheck(isAuthorized: status == .authorized || status == .notDetermined, resolve: resolve)
            }
            return
        }
        #endif
        // For iOS versions below 14, proceed without requesting permission.
        self.proceedWithIdfaCheck(isAuthorized: ASIdentifierManager.shared().isAdvertisingTrackingEnabled, resolve: resolve)
    }
    
    private func proceedWithIdfaCheck(isAuthorized: Bool, resolve: @escaping RCTPromiseResolveBlock) {
        var response: [String: Any] = [
            "isLimitAdTrackingEnabled": !isAuthorized,
            "advertisingId": ""
        ]
        
        if isAuthorized {
            let idfa = ASIdentifierManager.shared().advertisingIdentifier.uuidString
            response["advertisingId"] = idfa
        }
        
        resolve(response)
    }
}
