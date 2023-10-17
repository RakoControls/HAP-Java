package io.github.hapjava.services.impl;

import io.github.hapjava.accessories.HomekitAccessory;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithAccessoryFlags;
import io.github.hapjava.accessories.optionalcharacteristic.AccessoryWithHardwareRevision;
import io.github.hapjava.characteristics.impl.accessoryinformation.AccessoryFlagsCharacteristic;
import io.github.hapjava.characteristics.impl.accessoryinformation.FirmwareRevisionCharacteristic;
import io.github.hapjava.characteristics.impl.accessoryinformation.HardwareRevisionCharacteristic;
import io.github.hapjava.characteristics.impl.accessoryinformation.IdentifyCharacteristic;
import io.github.hapjava.characteristics.impl.accessoryinformation.ManufacturerCharacteristic;
import io.github.hapjava.characteristics.impl.accessoryinformation.ModelCharacteristic;
import io.github.hapjava.characteristics.impl.accessoryinformation.SerialNumberCharacteristic;
import io.github.hapjava.characteristics.impl.common.NameCharacteristic;

/** Accessory Information service. */
public class AccessoryInformationService extends AbstractServiceImpl {
  public static final String TYPE = "0000003E-0000-1000-8000-0026BB765291";

  public AccessoryInformationService(
      IdentifyCharacteristic identify,
      ManufacturerCharacteristic manufacturer,
      ModelCharacteristic model,
      NameCharacteristic name,
      SerialNumberCharacteristic serialNumber,
      FirmwareRevisionCharacteristic firmwareRevision) {
    super(TYPE);
    addCharacteristic(identify);
    addCharacteristic(manufacturer);
    addCharacteristic(model);
    addCharacteristic(name);
    addCharacteristic(serialNumber);
    addCharacteristic(firmwareRevision);
  }

  public AccessoryInformationService(HomekitAccessory accessory) {
    this(
        new IdentifyCharacteristic(
            value -> {
              if (value) {
                accessory.identify();
              }
            }),
        new ManufacturerCharacteristic(accessory::getManufacturer),
        new ModelCharacteristic(accessory::getModel),
        new NameCharacteristic(accessory::getName),
        new SerialNumberCharacteristic(accessory::getSerialNumber),
        new FirmwareRevisionCharacteristic(accessory::getFirmwareRevision));

    if (accessory instanceof AccessoryWithHardwareRevision) {
      addOptionalCharacteristic(
          new HardwareRevisionCharacteristic(
              ((AccessoryWithHardwareRevision) accessory)::getHardwareRevision));
    }
    if (accessory instanceof AccessoryWithAccessoryFlags) {
      addOptionalCharacteristic(
          new AccessoryFlagsCharacteristic(
              ((AccessoryWithAccessoryFlags) accessory)::getAccessoryFlags,
              ((AccessoryWithAccessoryFlags) accessory)::subscribeAccessoryFlags,
              ((AccessoryWithAccessoryFlags) accessory)::unsubscribeAccessoryFlags));
    }
  }

  public void addOptionalCharacteristic(HardwareRevisionCharacteristic hardwareRevision) {
    addCharacteristic(hardwareRevision);
  }

  public void addOptionalCharacteristic(AccessoryFlagsCharacteristic accessoryFlagsCharacteristic) {
    addCharacteristic(accessoryFlagsCharacteristic);
  }
}
