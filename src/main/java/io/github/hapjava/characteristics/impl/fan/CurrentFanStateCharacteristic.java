package io.github.hapjava.characteristics.impl.fan;

import io.github.hapjava.characteristics.EventableCharacteristic;
import io.github.hapjava.characteristics.HomekitCharacteristicChangeCallback;
import io.github.hapjava.characteristics.impl.base.EnumCharacteristic;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * This characteristic describes the current state of the fan. See {@link CurrentFanStateEnum} for
 * possible values.
 */
public class CurrentFanStateCharacteristic extends EnumCharacteristic<CurrentFanStateEnum>
    implements EventableCharacteristic {

  public CurrentFanStateCharacteristic(
      Supplier<CompletableFuture<CurrentFanStateEnum>> getter,
      Consumer<HomekitCharacteristicChangeCallback> subscriber,
      Runnable unsubscriber) {
    super(
        "000000AF-0000-1000-8000-0026BB765291",
        "uint8",
        "Current Fan State",
        CurrentFanStateEnum.values(),
        Optional.of(getter),
        Optional.empty(),
        Optional.of(subscriber),
        Optional.of(unsubscriber));
  }
}
