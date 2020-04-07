/*
 * Copyright (c) 2008-2020, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.client.impl.protocol.codec;

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.Generated;
import com.hazelcast.client.impl.protocol.codec.builtin.*;
import com.hazelcast.client.impl.protocol.codec.custom.*;

import javax.annotation.Nullable;

import static com.hazelcast.client.impl.protocol.ClientMessage.*;
import static com.hazelcast.client.impl.protocol.codec.builtin.FixedSizeTypesCodec.*;

/*
 * This file is auto-generated by the Hazelcast Client Protocol Code Generator.
 * To change this file, edit the templates or the protocol
 * definitions on the https://github.com/hazelcast/hazelcast-client-protocol
 * and regenerate it.
 */

/**
 * Copies all of the mappings from the specified map to this map (optional operation).The effect of this call is
 * equivalent to that of calling put(Object,Object) put(k, v) on this map once for each mapping from key k to value
 * v in the specified map.The behavior of this operation is undefined if the specified map is modified while the
 * operation is in progress.
 * Please note that all the keys in the request should belong to the partition id to which this request is being sent, all keys
 * matching to a different partition id shall be ignored. The API implementation using this request may need to send multiple
 * of these request messages for filling a request for a key set if the keys belong to different partitions.
 */
@Generated("31e34c6c5d6f83e937902f4c7ce4c81f")
public final class MapPutAllCodec {
    //hex: 0x012C00
    public static final int REQUEST_MESSAGE_TYPE = 76800;
    //hex: 0x012C01
    public static final int RESPONSE_MESSAGE_TYPE = 76801;
    private static final int REQUEST_TRIGGER_MAP_LOADER_FIELD_OFFSET = PARTITION_ID_FIELD_OFFSET + INT_SIZE_IN_BYTES;
    private static final int REQUEST_INITIAL_FRAME_SIZE = REQUEST_TRIGGER_MAP_LOADER_FIELD_OFFSET + BOOLEAN_SIZE_IN_BYTES;
    private static final int RESPONSE_INITIAL_FRAME_SIZE = RESPONSE_BACKUP_ACKS_FIELD_OFFSET + BYTE_SIZE_IN_BYTES;

    private MapPutAllCodec() {
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class RequestParameters {

        /**
         * name of map
         */
        public java.lang.String name;

        /**
         * mappings to be stored in this map
         */
        public java.util.List<java.util.Map.Entry<com.hazelcast.internal.serialization.Data, com.hazelcast.internal.serialization.Data>> entries;

        /**
         * should trigger MapLoader for elements not in this map
         */
        public boolean triggerMapLoader;

        /**
         * True if the triggerMapLoader is received from the client, false otherwise.
         * If this is false, triggerMapLoader has the default value for its type.
        */
        public boolean isTriggerMapLoaderExists;
    }

    public static ClientMessage encodeRequest(java.lang.String name, java.util.Collection<java.util.Map.Entry<com.hazelcast.internal.serialization.Data, com.hazelcast.internal.serialization.Data>> entries, boolean triggerMapLoader) {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        clientMessage.setRetryable(false);
        clientMessage.setOperationName("Map.PutAll");
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[REQUEST_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, REQUEST_MESSAGE_TYPE);
        encodeInt(initialFrame.content, PARTITION_ID_FIELD_OFFSET, -1);
        encodeBoolean(initialFrame.content, REQUEST_TRIGGER_MAP_LOADER_FIELD_OFFSET, triggerMapLoader);
        clientMessage.add(initialFrame);
        StringCodec.encode(clientMessage, name);
        EntryListCodec.encode(clientMessage, entries, DataCodec::encode, DataCodec::encode);
        return clientMessage;
    }

    public static MapPutAllCodec.RequestParameters decodeRequest(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        RequestParameters request = new RequestParameters();
        ClientMessage.Frame initialFrame = iterator.next();
        if (initialFrame.content.length >= REQUEST_TRIGGER_MAP_LOADER_FIELD_OFFSET + BOOLEAN_SIZE_IN_BYTES) {
            request.triggerMapLoader = decodeBoolean(initialFrame.content, REQUEST_TRIGGER_MAP_LOADER_FIELD_OFFSET);
            request.isTriggerMapLoaderExists = true;
        } else {
            request.isTriggerMapLoaderExists = false;
        }
        request.name = StringCodec.decode(iterator);
        request.entries = EntryListCodec.decode(iterator, DataCodec::decode, DataCodec::decode);
        return request;
    }

    @edu.umd.cs.findbugs.annotations.SuppressFBWarnings({"URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD"})
    public static class ResponseParameters {
    }

    public static ClientMessage encodeResponse() {
        ClientMessage clientMessage = ClientMessage.createForEncode();
        ClientMessage.Frame initialFrame = new ClientMessage.Frame(new byte[RESPONSE_INITIAL_FRAME_SIZE], UNFRAGMENTED_MESSAGE);
        encodeInt(initialFrame.content, TYPE_FIELD_OFFSET, RESPONSE_MESSAGE_TYPE);
        clientMessage.add(initialFrame);

        return clientMessage;
    }

    public static MapPutAllCodec.ResponseParameters decodeResponse(ClientMessage clientMessage) {
        ClientMessage.ForwardFrameIterator iterator = clientMessage.frameIterator();
        ResponseParameters response = new ResponseParameters();
        //empty initial frame
        iterator.next();
        return response;
    }

}
