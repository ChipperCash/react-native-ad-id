#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RNAdvertisingId, NSObject)

RCT_EXTERN_METHOD(getAdvertisingId:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
