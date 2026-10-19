import { runInAction } from "mobx";

export function resolvePromise(prms, promiseState) {
    promiseState.promise = prms;
    promiseState.data = null;
    promiseState.error = null;

    function promiseACB(data) {
        if (promiseState.promise === prms) {
            runInAction(function () {
                promiseState.data = data;
            });
        }
    }

    function errorACB(error) {
        if (promiseState.promise === prms) {
            runInAction(function () {
                promiseState.error = error;
            });
        }
    }

    prms.then(promiseACB).catch(errorACB);
}
