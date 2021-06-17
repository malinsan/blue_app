import React from 'react';
import {View, Text} from 'react-native';
import ProgressCircle from 'react-native-progress-circle'

export function StatusView() {
  return (
    <View>
      {/* SOC */}
      <View style={{ alignItems: "center", justifyContent: "center", backgroundColor: 'white', height: "60%"}}>
        <ProgressCircle
            percent={30}
            radius={50}
            borderWidth={8}
            color="#3399FF"
            shadowColor="#999"
            bgColor="#fff"
        >
            <Text style={{ fontSize: 18 }}>{'30%'}</Text>
        </ProgressCircle>
        <Text style={{ marginTop: 8, color: "darkgray" }}>STATE OF CHARGE</Text>
      </View>
      {/* Output */}
      <View style={{backgroundColor: 'red', height: 20}}></View>

      {/* Input */}
      <View style={{backgroundColor: 'green', height: 20}}></View>
    </View>
  );
}
