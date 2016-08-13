// Generated code from Butter Knife. Do not modify!
package mx.peta.nasaapp;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ListingActivity_ViewBinding<T extends ListingActivity> implements Unbinder {
  protected T target;

  public ListingActivity_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.toolbar = finder.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
    target.navigationView = finder.findRequiredViewAsType(source, R.id.listing_navigation_view, "field 'navigationView'", NavigationView.class);
    target.drawerLayout = finder.findRequiredViewAsType(source, R.id.listing_navigation_drawer, "field 'drawerLayout'", DrawerLayout.class);
    target.contentView = finder.findRequiredView(source, android.R.id.content, "field 'contentView'");
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.toolbar = null;
    target.navigationView = null;
    target.drawerLayout = null;
    target.contentView = null;

    this.target = null;
  }
}
