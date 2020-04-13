package pl.charmas.android.reactivelocation2.observables.geofence;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import io.reactivex.rxjava3.core.ObservableEmitter;
import pl.charmas.android.reactivelocation2.BaseFailureListener;
import pl.charmas.android.reactivelocation2.observables.ObservableContext;


class RemoveGeofenceRequestIdsObservableOnSubscribe extends RemoveGeofenceObservableOnSubscribe<Void> {
    private final List<String> geofenceRequestIds;

    RemoveGeofenceRequestIdsObservableOnSubscribe(ObservableContext ctx, List<String> geofenceRequestIds) {
        super(ctx);
        this.geofenceRequestIds = geofenceRequestIds;
    }

    @Override
    protected void removeGeofences(GeofencingClient geofencingClient, final ObservableEmitter<? super Void> emitter) {
        geofencingClient.removeGeofences(geofenceRequestIds)
                .addOnSuccessListener(aVoid -> emitter.onComplete())
                .addOnFailureListener(new BaseFailureListener<>(emitter));
    }
}
