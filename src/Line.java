import java.util.List;

public class Line {
    private Point firstPoint;
    private Point secondPoint;
    private Point middleOfthePoints;
    private Point theIntersect;

    public Line(Point firstPoint, Point secondPoint) {
        this.firstPoint = firstPoint;
        this.secondPoint = secondPoint;
    }
    public Line(double xFirstPoint, double yFirstPoint, double xSecondPoint, double ySecondPoint) {
        this.firstPoint = new Point(xFirstPoint, yFirstPoint);
        this.secondPoint = new Point(xSecondPoint, ySecondPoint);
    }
    public double length(){
        return Math.sqrt(this.firstPoint.getX() * this.secondPoint.getX() + this.firstPoint.getY() * this.secondPoint.getY());
    }
    public Point middle() {
        double midFirstPoint = (this.firstPoint.getX() + this.secondPoint.getX()) / 2.0D;
        double midSecondPoint = (this.firstPoint.getY() + this.secondPoint.getY()) / 2.0D;
        return new Point(midFirstPoint, midSecondPoint);
    }
    public Point start() {
        return this.firstPoint;
    }

    public Point end() {
        return this.secondPoint;
    }
    public boolean isIntersecting(Line var1) {
        Point var2 = this.start();
        Point var3 = this.end();
        Point var4 = var1.start();
        Point var5 = var1.end();
        double var6 = var2.getX();
        double var8 = var2.getY();
        double var10 = var3.getX();
        double var12 = var3.getY();
        double var14 = var4.getX();
        double var16 = var4.getY();
        double var18 = var5.getX();
        double var20 = var5.getY();
        double var22 = (var8 - var12) / (var6 - var10);
        double var24 = (var16 - var20) / (var14 - var18);
        if (var22 == var24) {
            return false;
        } else {
            double var30;
            double var32;
            double var34;
            double var36;
            if (var6 == var10 || var18 == var14) {
                double var28 = var1.getSlope() * (var6 - var14) + var16;
                var30 = Math.max(var6, var10);
                var32 = Math.min(var6, var10);
                var34 = Math.max(var8, var12);
                var36 = Math.min(var8, var12);
                double var38 = Math.max(var14, var18);
                double var40 = Math.min(var14, var18);
                double var42 = Math.max(var16, var20);
                double var44 = Math.min(var16, var20);
                if (var32 <= var6 && var6 <= var30 && var40 <= var6 && var6 <= var38 && var36 <= var28 && var28 <= var34 && var44 <= var28 && var28 <= var42) {
                    return true;
                }
            }

            Point var26 = new Point(var6, var8);
            Point var27 = new Point(var10, var12);
            Point var46 = new Point(var14, var16);
            Point var29 = new Point(var18, var20);
            var30 = this.orientation(var26, var27, var46);
            var32 = this.orientation(var26, var27, var29);
            var34 = this.orientation(var46, var29, var26);
            var36 = this.orientation(var46, var29, var27);
            if (var30 != var32 && var34 != var36) {
                return true;
            } else if (var30 == 0.0D && this.onSegment(var26, var46, var27)) {
                return true;
            } else if (var32 == 0.0D && this.onSegment(var26, var29, var27)) {
                return true;
            } else if (var34 == 0.0D && this.onSegment(var46, var26, var29)) {
                return true;
            } else {
                return var36 == 0.0D && this.onSegment(var46, var27, var29);
            }
        }
    }

    public Point intersectionWith(Line var1) {
        if (this.isIntersecting(var1)) {
            Point var2 = this.start();
            Point var3 = this.end();
            Point var4 = var1.start();
            Point var5 = var1.end();
            double var6 = var2.getX();
            double var8 = var2.getY();
            double var10 = var3.getX();
            double var12 = var3.getY();
            double var14 = var4.getX();
            double var16 = var4.getY();
            double var18 = var5.getX();
            double var20 = var5.getY();
            double var24;
            if (var6 == var10) {
                var24 = var1.getSlope() * (var6 - var14) + var16;
                return new Point(var6, var24);
            } else if (var14 == var18) {
                var24 = this.getSlope() * (var14 - var6) + var8;
                return new Point(var14, var24);
            } else {
                double var22 = (var8 - var12) / (var6 - var10);
                var24 = (var16 - var20) / (var14 - var18);
                double var26 = var8 - var22 * var6;
                double var28 = var16 - var24 * var14;
                double var30 = (var26 - var28) / (var24 - var22);
                double var32 = var22 * var30 + var26;
                return new Point(var30, var32);
            }
        } else {
            return null;
        }
    }

    public boolean equals(Line var1) {
        return this.firstPoint.getX() == var1.secondPoint.getX() && this.secondPoint.getX() == var1.secondPoint.getY();
    }

    public boolean onSegment(Point var1, Point var2, Point var3) {
        return var2.getX() <= Math.max(var1.getX(), var3.getX()) && var2.getX() >= Math.min(var1.getX(), var3.getX()) && var2.getY() <= Math.max(var1.getY(), var3.getY()) && var2.getY() >= Math.min(var1.getY(), var3.getY());
    }

    public double orientation(Point var1, Point var2, Point var3) {
        double var4 = (var2.getY() - var1.getY()) * (var3.getX() - var2.getX()) - (var2.getX() - var1.getX()) * (var3.getY() - var2.getY());
        if (var4 == 0.0D) {
            return 0.0D;
        } else {
            return var4 > 0.0D ? 1.0D : 2.0D;
        }
    }

    public double getSlope() {
        double var1 = this.firstPoint.getY() - this.secondPoint.getY();
        double var3 = this.firstPoint.getX() - this.secondPoint.getX();
        return var1 / var3;
    }

    public Point closestIntersectionToStartOfLine(Rectangle var1) {
        List var2 = var1.intersectionPoints(this);
        double var3 = 1.0D / 0.0;
        Point var5 = null;

        for(int var6 = 0; var6 < var2.size(); ++var6) {
            double var7 = this.start().distance((Point)var2.get(var6));
            if (var7 < var3) {
                var3 = var7;
                var5 = (Point)var2.get(var6);
            }
        }

        return var5;
    }

    public boolean isOnTheLine(Point var1) {
        if (var1 == null) {
            return false;
        } else {
            Line var2 = new Line(this.firstPoint, var1);
            Point var3 = var2.intersectionWith(this);
            if (var3 == null) {
                double var4 = Math.max(this.firstPoint.getX(), this.secondPoint.getX());
                double var6 = Math.max(this.firstPoint.getY(), this.secondPoint.getY());
                double var8 = Math.floor(Math.min(this.firstPoint.getX(), this.secondPoint.getX()));
                double var10 = Math.floor(Math.min(this.firstPoint.getY(), this.secondPoint.getY()));
                if (var1.getX() >= var8 && var1.getX() <= var4 && var1.getY() >= var10 && var1.getY() <= var6) {
                    return true;
                }
            }

            return false;
        }
    }
}
