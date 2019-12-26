package com.boa.gltest.ui.render;

import android.content.Context;

import com.boa.gltest.global.model.Item;
import com.pedrogomez.renderers.Renderer;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class ItemRendererBuilder extends RendererBuilder<Item> {
    public ItemRendererBuilder(Context context, ItemRenderer.OnItemClicked itemClicked) {
        Collection<Renderer<Item>> prototypes = getPrototypes(context, itemClicked);
        setPrototypes(prototypes);
    }

    private List<Renderer<Item>> getPrototypes(Context context, ItemRenderer.OnItemClicked itemClicked) {
        List<Renderer<Item>> prototypes = new LinkedList<>();
        ItemRenderer itemRenderer = new ItemRenderer(context, itemClicked);
        prototypes.add(itemRenderer);
        return prototypes;
    }

    @Override
    protected Class getPrototypeClass(Item content) {
        return ItemRenderer.class;
    }
}
