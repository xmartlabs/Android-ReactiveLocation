package pl.charmas.android.reactivelocation2.observables.geofence;

import android.app.PendingIntent;

import com.google.android.gms.location.GeofencingClient;
import com.google.android.gms.tasks.OnSuccessListener;

import io.reactivex.rxjava3.core.ObservableEmitter;
import pl.charmas.android.reactivelocation2.BaseFailureListener;
import pl.charmas.android.reactivelocation2.observables.ObservableContext;


class RemoveGeofenceByPendingIntentObservableOnSubscribe extends BaseGeofencingObservableOnSubscribe<Void> {
    private final PendingIntent pendingIntent;

    RemoveGeofenceByPendingIntentObservableOnSubscribe(ObservableContext ctx, PendingIntent pendingIntent) {
        super(ctx);
        this.pendingIntent = pendingIntent;
    }

    @Override
    protected void onGeofencingClientReady(GeofencingClient geofencingClient, final ObservableEmitter<? super Void> emitter) {
        geofencingClient.removeGeofences(pendingIntent)
                .addOnSuccessListener(aVoid -> emitter.onComplete())
                .addOnFailureListener(new BaseFailureListener<>(emitter));
    }
}
