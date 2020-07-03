package com.healthyfoody.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;
import com.healthyfoody.validation.annotations.GUID;
import lombok.EqualsAndHashCode;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@JsonSerialize(using = GUIDWrapper.Serializer.class)
@JsonDeserialize(using = GUIDWrapper.Deserializer.class)
@EqualsAndHashCode
public class GUIDWrapper implements Serializable, Comparable<UUID> {
    @GUID
    UUID id;

    public GUIDWrapper(UUID id){
        this.id = id;
    }

    public UUID value() {
        return this.id;
    }

    @Override
    public int compareTo(UUID val) {
        return id.compareTo(val);
    }

    public static List<UUID> unwrap(Collection<GUIDWrapper> source) {
        List<UUID> target = new ArrayList<>();
        for (GUIDWrapper val : source) {
            target.add(val.value());
        }
        return target;
    }

    public static class Serializer extends JsonSerializer<GUIDWrapper> {

        @Override
        public void serialize(GUIDWrapper wrapper, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
            generator.writeString(wrapper.id.toString());
        }
    }

    public static class Deserializer extends FromStringDeserializer<GUIDWrapper> {

        protected Deserializer() {
            super(GUIDWrapper.class);
        }

        @Override
        protected GUIDWrapper _deserialize(String s, DeserializationContext deserializationContext) throws IOException {
            UUID id = UUID.fromString(s);
            return new GUIDWrapper(id);
        }
    }
}