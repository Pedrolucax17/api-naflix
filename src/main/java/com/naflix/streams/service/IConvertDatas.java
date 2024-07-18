package com.naflix.streams.service;

public interface IConvertDatas {
    <T> T getDatas(String json, Class<T> tClass);
}
