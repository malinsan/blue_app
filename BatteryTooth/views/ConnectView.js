import React from "react"
import {
    Button,
    Text,
    View,
    NativeModules,
  } from 'react-native';
  

export function ConnectView() {
    const viewStyle = {
        alignItems: "center",
        justifyContent: "center",
        height: 150
    }

    return (
        <View style={viewStyle}>
            <Button
                onPress={() => {
                    console.log("Connect to first best device found")
                    BGXModule.scanForDevices()
                }}
                title="Connect to Device"
                color="#841584"
                accessibilityLabel="Scan for BGX Devices"
            />
        </View>
    )
}
