# react-native-ad-id

A React Native module for accessing device advertising IDs with support for iOS and Android platforms.

## Installation

```sh
npm install react-native-ad-id
```

It should autolink for android. You can then navigate to your iOS folder and do

```sh
pod install
```

## Usage

```js
import { getAdvertisingId } from 'react-native-ad-id'

// ...

try {
    const { advertisingId, isLimitAdTrackingEnabled } = await getAdvertisingId()
} catch (error) {
    console.log('Unable to get advertising Id', error)
}
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)
