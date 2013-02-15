package org.apache.drill.optiq;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.drill.jdbc.DrillTable;

import org.eigenbase.rel.TableAccessRelBase;
import org.eigenbase.relopt.RelOptCluster;
import org.eigenbase.relopt.RelOptPlanner;
import org.eigenbase.relopt.RelOptTable;
import org.eigenbase.relopt.RelTraitSet;

/**
 * Scan of a Drill table.
 */
public class DrillScan extends TableAccessRelBase implements DrillRel {
  private final DrillTable drillTable;

  /** Creates a DrillScan. */
  public DrillScan(RelOptCluster cluster,
      RelTraitSet traits,
      RelOptTable table)
  {
    super(cluster, traits, table);
    assert traits.contains(DrillOptiq.CONVENTION);
    this.drillTable = table.unwrap(DrillTable.class);
    assert drillTable != null;
  }

  @Override
  public void register(RelOptPlanner planner) {
    super.register(planner);
    DrillOptiq.registerStandardPlannerRules(planner);
  }

  public void implement(DrillImplementor implementor) {
    final ObjectNode node = implementor.mapper.createObjectNode();
    node.put("op", "scan");
    node.put("memo", "initial_scan");
    node.put("ref", "donuts");
    node.put("source", drillTable.dataSource.getName());
    implementor.add(node);
  }
}

// End DrillScan.java
