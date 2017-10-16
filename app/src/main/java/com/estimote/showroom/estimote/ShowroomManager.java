package com.estimote.showroom.estimote;

import android.content.Context;
import android.util.Log;

import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Nearable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

public class ShowroomManager {

    private Listener listener;

    private BeaconManager beaconManager;
    private String scanId;


    public ShowroomManager(Context context, final Map<NearableID, Product> products) {
        beaconManager = new BeaconManager(context);
        //Callback to be invoked when nearables are discovered nearby.
        beaconManager.setNearableListener(new BeaconManager.NearableListener() {
            @Override
            public void onNearablesDiscovered(List<Nearable> list) {
                for (Nearable nearable : list) {
                    NearableID nearableID = new NearableID(nearable.identifier);
                    if (!products.keySet().contains(nearableID)) { continue; }

                    Product product = products.get(nearableID);
                    product.setRssi(nearable.rssi);
                    listener.onProductPickup(product);
                }
            }
        });
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    public interface Listener {
        void onProductPickup(Product product);
        void onConnectedToService();
    }

    public void startUpdates() {
        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                scanId = beaconManager.startNearableDiscovery();
            }
        });
    }

    public void stopUpdates() {
        beaconManager.stopNearableDiscovery(scanId);
    }

    public void destroy() {
        beaconManager.disconnect();
    }
}
