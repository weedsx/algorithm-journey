package class140;

// 机器人的移动区域
// 二维网格中只有x和y的值都为整数的坐标，才叫格点
// 某个机器人从格点(0,0)出发，每次机器人都走直线到达(x + dx, y + dy)的格点
// 一共移动n次，每次的(dx, dy)都给定，输入保证机器人最终回到(0,0)位置
// 机器人走的路线所围成的区域一定是多边形，从起点出发最终回到起点，途中路线不再相交
// 返回多边形的内部一共几个格点，多边形的边上一共几个格点，多边形的面积
// 3 <= n <= 100
// -100 <= dx、dy <= 100
// 测试链接 : http://poj.org/problem?id=1265
// 提交以下的code，提交时请把类名改成"Main"，可以通过所有测试用例

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;

public class Code04_Area {

	public static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer in = new StreamTokenizer(br);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		in.nextToken();
		int cases = (int) in.nval;
		for (int t = 1; t <= cases; t++) {
			in.nextToken();
			int n = (int) in.nval;
			int edges = 0;
			double area = 0;
			for (int i = 1, x = 0, y = 0, dx, dy; i <= n; i++) {
				in.nextToken();
				dx = (int) in.nval;
				in.nextToken();
				dy = (int) in.nval;
				edges += gcd(Math.abs(dx), Math.abs(dy));
				area += x * (y + dy) - (x + dx) * y;
				x += dx;
				y += dy;
			}
			// 叉积的结果是两个向量做边的平行四边形面积，最终得到的结果需要除以2
			area /= 2;
			// pick定理
			// 如果一个多边形的顶点都是格点(坐标都为整数)，多边形面积 = 边界上格点数/2 + 内部格点数 - 1
			// 所以，内部格点数 = 多边形面积 - 边界上格点数/2 + 1
			int inners = (int) (area) - edges / 2 + 1;
			out.println("Scenario #" + t + ":");
			out.print(inners + " ");
			out.print(edges + " ");
			out.printf("%.1f", area);
			out.println();
			out.println();
		}
		out.flush();
		out.close();
		br.close();
	}

}
