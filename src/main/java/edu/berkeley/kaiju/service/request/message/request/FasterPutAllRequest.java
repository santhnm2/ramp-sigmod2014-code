package edu.berkeley.kaiju.service.request.message.request;

import edu.berkeley.kaiju.data.DataItem;
import edu.berkeley.kaiju.exception.KaijuException;
import edu.berkeley.kaiju.service.LockManager;
import edu.berkeley.kaiju.service.MemoryStorageEngine;
import edu.berkeley.kaiju.service.request.message.KaijuMessage;
import edu.berkeley.kaiju.service.request.message.response.KaijuResponse;

import java.util.Map;

public class FasterPutAllRequest extends KaijuMessage implements IKaijuRequest {
    public Map<String, DataItem> keyValuePairs;
    public long timestamp;

    private FasterPutAllRequest() {}

    public FasterPutAllRequest(Map<String, DataItem> keyValuePairs, long timestamp) {
        this.keyValuePairs = keyValuePairs;
        this.timestamp = timestamp;
    }

    @Override
    public KaijuResponse processRequest(MemoryStorageEngine storageEngine, LockManager lockManager) throws
                                                                                                    KaijuException {
        storageEngine.prepare(keyValuePairs);
        storageEngine.commit(timestamp);
        return new KaijuResponse();
    }
}