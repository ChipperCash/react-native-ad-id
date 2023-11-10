import * as React from 'react';
import { useState } from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { getAdvertisingId } from 'react-native-ad-id';

export default function App() {
  const [advertisingId, setAdvertisingId] = useState<string | undefined>(
    undefined
  );
  const [isLimitAdTrackingEnabled, setIsLimitAdTrackingEnabled] = useState<
    boolean | undefined
  >(undefined);

  React.useEffect(() => {
    getAdvertisingId().then((res) => {
      console.log({ res });
      setAdvertisingId(res.advertisingId);
      setIsLimitAdTrackingEnabled(res.isLimitAdTrackingEnabled);
    });
  }, []);

  return (
    <View style={styles.container}>
      <Text>Advert Id: {advertisingId}</Text>
      <Text>Tracking Enabled: {isLimitAdTrackingEnabled ? 'YES' : 'NO'}</Text>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
