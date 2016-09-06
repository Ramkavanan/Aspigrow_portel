package com.aspigrow.portel.convertor;

public interface Convertor<M, E> {
	
    E convertToEntity(M model);

    M convertToModel(E entity);

    void updateEntityWithModel(E toUpdate, M model);
}
