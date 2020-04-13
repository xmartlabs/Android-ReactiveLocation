package pl.charmas.android.reactivelocation2.observables;

import com.google.android.gms.common.api.Response;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;


public class TaskSingleOnSubscribe<T extends Response> implements SingleOnSubscribe<T> {
    private final Task<T> task;

    public TaskSingleOnSubscribe(Task<T> task) {
        this.task = task;
    }

    @Override
    public void subscribe(final SingleEmitter<T> emitter) {
        task.addOnSuccessListener(t -> {
            if (!emitter.isDisposed()) {
                emitter.onSuccess(t);
            }
        }).addOnFailureListener(exception -> {
            if (!emitter.isDisposed()) {
                emitter.onError(exception);
            }
        });
    }
}
