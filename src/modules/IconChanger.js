import { NativeModules } from 'react-native';
const { IconChanger } = NativeModules;

export default {
  changeIcon: (iconName) => IconChanger.changeIcon(iconName),
};