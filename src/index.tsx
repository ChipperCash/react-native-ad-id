import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-testing-testing' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

  const AdIdentifier = NativeModules.RNAdvertisingId
  ? NativeModules.RNAdvertisingId
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function getAdvertisingId(): Promise<{
  advertisingId: string;
  isLimitAdTrackingEnabled: boolean;
}> {
  return AdIdentifier.getAdvertisingId();
}
